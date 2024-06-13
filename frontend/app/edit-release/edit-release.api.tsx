import { release, engineer } from "./release.types";
import { useRouter } from "next/navigation";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function putRelease(
    Release: release,
    ID: number
): Promise<number> {
    
    const res = await fetch(`${API_URL}/releases/${ID}`, {
        method: "PUT",
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

export async function getRelease(id: number): Promise<release> {
    const res = await fetch(`${API_URL}/releases/${id}`);
    const data = await res.json();
    return data;
}
