"use client"
import React, { ChangeEvent } from 'react';
import { Input } from "@nextui-org/input";

interface UsernameInputProps {
    value: string;
    onChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

const UsernameInput: React.FC<UsernameInputProps> = ({ value, onChange }) => {
    return (
        <div className="mb-4">
            <label htmlFor="username" className="block text-white text-sm font-bold mb-2">
                Usuario
            </label>
            <Input
                type="text"
                id="username"
                value={value}
                onChange={onChange}
                className="w-full"
                placeholder="Introduce tu usuario"
            />
        </div>
    );
};

export default UsernameInput;
