"use client";
import { useEffect, useState } from "react";
import { login } from "./login.api";


const useLogin = () => {
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [message, setMessage] = useState<string>('');

    const handleLoginClick = async () => {
        try {
            const response = await login(username, password);
            if (response.success) {
                localStorage.setItem('isAuthenticated', 'true');
                window.location.href = '/create-release';
                setMessage('Login successful');
            } else {
                console.log('Login failed: Invalid Username or Password', response.message);
                setMessage('Login failed: Invalid Username or Password');
            }
        } catch (error: any) {
            setMessage(error.message || 'An error occurred');
        }
    };

    const handleCancelClick = () => {
        console.log("Cancel clicked");
        window.location.href = "/releases-dashboard";
    };

    return {
        username, setUsername, password, setPassword, message, handleLoginClick, handleCancelClick
    };

};

export default useLogin;
