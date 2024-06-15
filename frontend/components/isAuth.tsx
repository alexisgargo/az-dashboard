"use client";
import { useEffect } from "react";
import { redirect } from "next/navigation";

export default function isAuth(Component: any) {
    return function IsAuth(props: any) {
        useEffect(() => {
            const auth = localStorage.getItem("isAuthenticated");
            if (!auth) {
                return redirect("/login");
            }
        }, []);

        return <Component {...props} />;
    };
}
