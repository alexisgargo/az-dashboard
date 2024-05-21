"use client";
import React from 'react';
import { Card, CardBody } from "@nextui-org/card";
import UsernameInput from '../../components/UsernameInput';
import PasswordInput from '../../components/PasswordInput';
import LoginButton from '../../components/LoginButton';
import CancelButton from '../../components/CancelButton';
import useLogin from './login.hook';
import { title } from "@/components/primitives";

const LoginPage: React.FC = () => {
    const { username, setUsername, password, setPassword, message, handleLoginClick } = useLogin();

    const handleCancelClick = () => {
        console.log("Cancel clicked");
    };

    return (
        <div className="flex justify-center items-center min-h-screen bg-black-900">
            <Card className="w-full max-w-md">
                <CardBody className="p-6">
                    <h1 className={`${title()} mb-6`}>Iniciar Sesión</h1> {/* Añadir margen inferior */}
                    <UsernameInput value={username} onChange={(e) => setUsername(e.target.value)} />
                    <PasswordInput value={password} onChange={(e) => setPassword(e.target.value)} />
                    <div className="flex justify-between mt-4">
                        <LoginButton onClick={handleLoginClick} />
                        <CancelButton onClick={handleCancelClick} />
                    </div>
                    <p className="mt-4 text-red-500">{message}</p>
                </CardBody>
            </Card>
        </div>
    );
}

export default LoginPage;
