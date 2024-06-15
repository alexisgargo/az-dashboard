"use client";
import React from 'react';
import { CreateReleaseForm } from "@/components/releaseManagement/createReleaseForm";
import isAuth from "@/components/isAuth";

const createReleasePage = () => {
    return (
        <div className="w-full">
            <h1 className="text-3xl font-bold text-center">
                Create a new release
            </h1>
            <CreateReleaseForm />
        </div>
    );
  };
export default isAuth(createReleasePage);

