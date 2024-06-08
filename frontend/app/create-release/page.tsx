"use client";
import React from 'react';
import useAuth from './auth.hook';
import { CreateReleaseForm } from "@/components/releaseManagement/createReleaseForm";

export default function createReleasePage() {
    const {} = useAuth();
    return (
        <div className="w-full">
            <h1 className="text-3xl font-bold text-center">
                Create a new release
            </h1>
            <CreateReleaseForm />
        </div>
    );

}
