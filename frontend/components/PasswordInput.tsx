"use client"
import React, { ChangeEvent } from 'react';
import { Input } from "@nextui-org/input";

interface PasswordInputProps {
    value: string;
    onChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

const PasswordInput: React.FC<PasswordInputProps> = ({ value, onChange }) => {
    return (
        <div className="mb-4">
            <label htmlFor="password" className="block text-white text-sm font-bold mb-2">
                Contraseña
            </label>
            <Input
                type="password"
                id="password"
                value={value}
                onChange={onChange}
                className="w-full"
                placeholder="Introduce tu contraseña"
            />
        </div>
    );
};

export default PasswordInput;
