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
            setMessage('Login successful');
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
