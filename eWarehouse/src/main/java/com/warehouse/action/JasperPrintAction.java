/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.warehouse.action;

import com.genrep.codex.procedure.ClassificationProcedure;
import com.genrep.container.service.application.AppFactory;
import com.genrep.guimodel.service.action.AAction;
import com.genrep.system.service.AppSystem;
import com.icesoft.faces.context.effects.JavascriptContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author simon
 */
public abstract class JasperPrintAction extends AAction {

    protected Map jasperParams = new HashMap();

    @Override
    public boolean doExecute() {
        try {
            Connection connection = getConnection();

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                    getExternalContext().getSession(true);
            String savePath = session.getServletContext().getRealPath(File.separatorChar + PrintConst.Def_SaveDir);
            File tempDir = new File(savePath + File.separatorChar + session.getId());
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }

            final String jrxmlDir = session.getServletContext().getRealPath(File.separatorChar + "WEB-INF" + File.separatorChar + "classes" + File.separatorChar + getReportsDir());
            String jrxmlPath = jrxmlDir + File.separatorChar + getInputFilePath();
            FileInputStream is = new FileInputStream(new File(jrxmlPath));

            //TODO: pomesteno vo preCalculations();
//            params = prepareParams();

            if (!preCalculations()) {
                getContainer().setMessage("Pre-Calculations have failed!!!");
                return false;
            }

//            params.put(JRParameter.REPORT_FILE_RESOLVER, new FileResolver() {
//
//                public File resolveFile(String string) {
//                    return new File(jrxmlDir + File.separatorChar + string);
//                }
//            });
            jasperParams.put(JRParameter.REPORT_CLASS_LOADER, new JasperActionClassLoader(jrxmlDir));
            Locale locale = new Locale("mk", "MK");
            jasperParams.put(JRParameter.REPORT_LOCALE, locale);
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            JasperCompileManager.compileReportToStream(is, os);
            FileOutputStream fos = new FileOutputStream(tempDir.getAbsolutePath() + File.separatorChar + getOutFileName());

            JasperRunManager.runReportToPdfStream(new ByteArrayInputStream(os.toByteArray()), fos, jasperParams, connection);

            // JasperRunManager.runReportToPdfFile(jrxmlPath , tempDir.getAbsolutePath() + File.separatorChar + getOutFileName(), prepareParams(), connection);
            fos.flush();
            fos.close();
            is.close();
            //            if (proc.getOpSession().isConnected()) {
            //                proc.getOpSession().close();
            //            }
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                AppSystem.getLogger().error("Ne ja zatvara konekcijata");
                AppSystem.getLogger().error(ex.getMessage(), ex);
            }

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String location = request.getRequestURL().toString();
            location = location.substring(0, location.indexOf(request.getContextPath()));
            location = location.concat(request.getContextPath() + "/" + PrintConst.Def_SaveDir + "/" + tempDir.getName() + "/");
            try {
                JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "window.open(\"" + location + getOutFileName() + "\", 'myWindow');");
            } catch (Exception ex) {
                AppSystem.getLogger().error(ex.getMessage(), ex);
                return false;
            }
            return true;
        } catch (IOException ex) {
            AppSystem.getLogger().error(ex.getMessage(), ex);
            return false;
        } catch (JRException ex) {
            AppSystem.getLogger().error(ex.getMessage(), ex);
            return false;
        }

    }

    public abstract Map prepareParams();

    public boolean preCalculations() {
        jasperParams = prepareParams();
        //TODO: this is the method for some pre db calculations: views, conversions, etc...
        return true;
    }

    public abstract String getInputFilePath();

    public String getOutFileName() {
        return PrintConst.pdfDefaultFilename;
    }

    public String getReportsDir() {
        return "Reports";
    }

    public Connection getConnection() {
        ClassificationProcedure proc = new ClassificationProcedure(AppFactory.getCurrentApplication().getClass().getName(), "print session");
        return proc.getOpSession().connection();
    }

    class JasperActionClassLoader extends ClassLoader {

        String repDir;

        public JasperActionClassLoader(String repDir) {
            this.repDir = repDir;
        }

        @Override
        public URL getResource(String string) {
            AppSystem.getLogger().info("Get Resource " + string);
            if (string.endsWith(".jasper")) {
                try {
                    File f = new File(repDir + File.separatorChar + string);
                    if (!f.exists()) {
                        File template = new File(repDir + File.separatorChar + f.getName().replace(".jasper", ".jrxml"));
                        AppSystem.getLogger().info("Compiling... " + template.getName());
                        JasperCompileManager.compileReportToFile(template.getAbsolutePath(), f.getAbsolutePath());
                    }
                    return f.toURI().toURL();
                } catch (MalformedURLException ex) {
                    AppSystem.getLogger().info(ex.getMessage(), ex);
                } catch (JRException ex) {
                    AppSystem.getLogger().info(ex.getMessage(), ex);
                }
            }
            return super.getResource(string);
        }

        @Override
        public InputStream getResourceAsStream(String string) {
            AppSystem.getLogger().info("Get Resource as Stream");
            return super.getResourceAsStream(string);
        }
    }
}
