"use client";
import { useEffect, useState } from "react";
import { login } from "./login.api";
import { redirect } from "next/navigation";

const useLogin = () => {
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [message, setMessage] = useState<string>("");

    const handleLoginClick = async () => {
        try {
            const response = await login(username, password);
            if (response.success) {
                localStorage.setItem("isAuthenticated", "true");
                window.location.href = "/admin-menu";
                setMessage("Login successful");
            } else {
                setMessage("Login failed: Invalid Username or Password");
            }
        } catch (error: any) {
            setMessage(error.message || "An error occurred");
        }
    };

    const handleCancelClick = () => {
        console.log("Cancel clicked");
        window.location.href = "/";
    };

    return {
        username,
        setUsername,
        password,
        setPassword,
        message,
        handleLoginClick,
        handleCancelClick,
    };
};

export default useLogin;
