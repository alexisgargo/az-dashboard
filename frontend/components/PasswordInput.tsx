"use client"
import React, { useState } from 'react';

const PasswordInput = () => {
    const [password, setPassword] = useState('');

    return (
        <div className="mb-4">
            <label htmlFor="password" className="block text-gray-700 text-sm font-bold mb-2">
                Contraseña
            </label>
            <input
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                placeholder="Introduce tu contraseña"
            />
        </div>
    );
};

export default PasswordInput;
