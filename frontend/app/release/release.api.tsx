import { release, releaseProgress, issueCount } from "./release.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function getRelease(id: number): Promise<release> {
    const res = await fetch(`${API_URL}/releases/${id}`);
    const data = await res.json();
    return data;
}

export async function getHistoricalProgress(
    date: string,
    idRelease: number
): Promise<releaseProgress> {
    const res = await fetch(
        `${API_URL}/az_dashboard/historical/${date}/${idRelease}`
    );
    const data = await res.json();
    return data;
}

export async function getHistoricalIssueCount(
    date: string,
    idRelease: number
): Promise<issueCount> {
    const res = await fetch(
        `${API_URL}/az_dashboard/issues/count/${date}/${idRelease}`
    );
    const data = await res.json();
    return data;
}
