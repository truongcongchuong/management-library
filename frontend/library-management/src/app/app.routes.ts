import { Routes } from '@angular/router';
import { Component } from '@angular/core';
import { PublicLayout } from './layouts/public-layout/public-layout';
import { MainLayout } from './layouts/main-layout/main-layout';
import { Layout } from "./features/auth/layout/layout";
import { Login } from "./features/auth/component/login/login";
import { Register } from "./features/auth/component/register/register";
import { Landing } from "./features/public/landing/landing";

import { Dashboard } from './features/admin/dashboard/dashboard/dashboard';
import { BookManagement } from './features/admin/book-management/book-management/book-management';
import { UserManagement } from './features/admin/user-management/user-management/user-management';
import { BorrowManagement } from './features/admin/borrow-management/borrow-management/borrow-management';
import { Profile } from './features/admin/profile/profile';


export const routes: Routes = [
    {
        path: '',
        component: PublicLayout,
        children: [
            {
                path: 'auth',
                component: Layout,
                children: [
                    {
                        path: 'login',
                        component: Login
                    },
                    {
                        path: 'register',
                        component: Register
                    }
                ]
            },
            {
                path: '',
                component: Landing
            }
        ]
    },

    {
        path: 'admin',
        component: MainLayout,
        children: [
            {
                path: 'dashboard',
                component: Dashboard
            },
            {
                path: 'book-management',
                component: BookManagement
            },
            {
                path: 'user-management',
                component: UserManagement
            },
            {
                path: 'borrow-management',
                component: BorrowManagement
            },
            {
                path: 'profile',
                component: Profile
            }
        ]
    }
];
