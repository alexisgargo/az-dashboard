import { release, engineer } from "@/app/create-release/release.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function postCreateRelease(Release: release): Promise<release> {
    const res = await fetch(`${API_URL}/releases`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(Release),
    });
    const data = await res.json();
    console.log(data);
    return data;
}

export async function getEngineers(): Promise<engineer[]> {
    const res = await fetch(`${API_URL}/engineers`);
    const data = await res.json();
    return data;
}
