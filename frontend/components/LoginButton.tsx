"use client";
import React from 'react';
import { Button } from "@nextui-org/button";

interface LoginButtonProps {
    onClick: () => void; 
}

const LoginButton = ({ onClick }: LoginButtonProps) => {
    return (
        <Button
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            onClick={onClick}
        >
            Log in
        </Button>
    );
};

export default LoginButton;
