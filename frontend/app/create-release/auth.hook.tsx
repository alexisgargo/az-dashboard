"use client";
import {useLayoutEffect, useState } from "react";

const useAuth = () => {
    useLayoutEffect(() => {
        if (!localStorage.getItem('isAuthenticated')) {
            window.location.href = '/login';
        }
    }, []);

    return{};

};

export default useAuth;