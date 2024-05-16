"use client"
import React from 'react';

interface CancelButtonProps {
    onClick: () => void;
}

const CancelButton = ({ onClick }: CancelButtonProps) => {
    return (
        <button
            className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            onClick={onClick}
        >
            Cancelar
        </button>
    );
};

export default CancelButton;
