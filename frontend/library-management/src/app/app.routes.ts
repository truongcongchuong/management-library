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
import { Home } from './features/user/home/home/home';
import { SearchBook } from './features/user/search-book/search-book/search-book';
import { History } from './features/user/history/history/history';
import { Profile } from './shared/components/profile/profile';
import { adminGuard } from './core/guards/admin-guard';
import { authGuard } from './core/guards/auth-guard';


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
        path: '',
        component: MainLayout,
        canActivate: [authGuard],
        children: [
            {
                path: 'admin',
                canActivate: [adminGuard],
                children: [
                    {
                        path: '',
                        redirectTo: 'dashboard',
                        pathMatch: 'full'
                    },
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
            },

            {
                path: "user",
                children: [
                    {
                        path: '',
                        redirectTo: 'home',
                        pathMatch: 'full'
                    },
                    {
                        path: 'home',
                        component: Home
                    },
                    {
                        path: 'search-book',
                        component: SearchBook
                    },
                    {
                        path: 'history',
                        component: History
                    },
                    {
                        path: 'profile',
                        component: Profile
                    }
                ]
            }
            
        ]
    },
    {
        path: '**',
        redirectTo: ''
    }
];
