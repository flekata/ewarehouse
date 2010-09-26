/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genrep.warehouse.app;

import com.genrep.container.service.app.acl.IUser;
import com.genrep.container.service.application.IApplicationImpl;
import com.genrep.guimodel.app.DefaultApplication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author user3
 */
public class EWarehouseApp extends DefaultApplication {

    public EWarehouseApp(IApplicationImpl appImplementation) {
        super(appImplementation);
    }

    @Override
    public IUser getAuthenticatedUser() {
        IUser principal = (IUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }
}
