import { release, engineer } from "@/app/create-release/release.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function postCreateRelease(Release: release): Promise<number> {

    const res = await fetch(`${API_URL}/releases`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(Release),
    });

    return res.status;
}

export async function getEngineers(): Promise<engineer[]> {
    const res = await fetch(`${API_URL}/engineers`);
    const data = await res.json();
    return data;
}
