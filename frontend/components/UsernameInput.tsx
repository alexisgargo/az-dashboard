import React, { useState } from 'react';

const UsernameInput = () => {
    const [username, setUsername] = useState('');

    return (
        <div className="mb-4">
            <label htmlFor="username" className="block text-gray-700 text-sm font-bold mb-2">
                Usuario
            </label>
            <input
                type="text"
                id="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                placeholder="Introduce tu usuario"
            />
        </div>
    );
};

export default UsernameInput;
