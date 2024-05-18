"use client";
import React, { useState } from 'react';
import LoginLayout from './layout';
import { title } from "@/components/primitives";
import UsernameInput from '../../components/UsernameInput';
import PasswordInput from '../../components/PasswordInput';
import LoginButton from '../../components/LoginButton';
import CancelButton from '../../components/CancelButton';
import useLogin from './login.hook';

const LoginPage: React.FC = () => {
    const { username, setUsername, password, setPassword, message, handleLoginClick } = useLogin();

    const handleCancelClick = () => {
        console.log("Cancel clicked");
    };

    return (
        <div>
            <h1 className={title()}>Iniciar Sesión</h1>
            <UsernameInput value={username} onChange={(e) => setUsername(e.target.value)} />
            <PasswordInput value={password} onChange={(e) => setPassword(e.target.value)} />
            <div className="flex justify-between mt-4">
                <LoginButton onClick={handleLoginClick} />
                <CancelButton onClick={handleCancelClick} />
            </div>
            <p>{message}</p>
        </div>
    );
}

export default LoginPage;
