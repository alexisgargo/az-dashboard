import React from 'react';
import LoginLayout from './layout';
import { title } from "@/components/primitives";
import UsernameInput from '../../components/UsernameInput';
import PasswordInput from '../../components/PasswordInput';
import LoginButton from '../../components/LoginButton';
import CancelButton from '../../components/CancelButton';

export default function LoginPage() {
    const handleLoginClick = () => {
        console.log("Login clicked");
    };

    const handleCancelClick = () => {
        console.log("Cancel clicked");
    };

    return (
        <LoginLayout>
            <h1 className={title()}>Iniciar Sesión</h1>
            <UsernameInput />
            <PasswordInput />
            <div className="flex justify-between mt-4">
                <LoginButton onClick={handleLoginClick} />
                <CancelButton onClick={handleCancelClick} />
            </div>
        </LoginLayout>
    );
}
