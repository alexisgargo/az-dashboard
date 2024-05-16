"use client"
import React from 'react';

interface LoginButtonProps {
    onClick: () => void; // Función a ejecutar cuando se hace clic en el botón
}

const LoginButton = ({ onClick }: LoginButtonProps) => {
    return (
        <button
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            onClick={onClick}
        >
            Log in
        </button>
    );
};

export default LoginButton;
