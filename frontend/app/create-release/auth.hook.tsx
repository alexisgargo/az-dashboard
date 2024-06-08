"use client";
import { useEffect, useState } from "react";

const useAuth = () => {
    useEffect(() => {
        if (!localStorage.getItem('isAuthenticated')) {
            window.location.href = '/login';
        }
    }, []);

    return{};

};

export default useAuth;