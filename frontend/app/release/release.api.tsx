import { release, releaseProgress } from "./release.types";

const API_URL = "http://localhost:8080/az_dashboard";

export async function getRelease(id: number): Promise<release> {
    const res = await fetch(`${API_URL}/release/${id}`);
    const data = await res.json();
    console.log(data);
    return data;
}

export async function getProgress(
    project: string,
    version: string
): Promise<releaseProgress> {
    // console.log(`project: ${project}, version: ${version}`);
    // console.log(`${API_URL}/progress/${project}/${version}`);
    const res = await fetch(`${API_URL}/progress/${project}/${version}`);
    const data = await res.json();
    // console.log(data);
    return data;
}

export async function getHistoricalProgress(date: string, id: number,): Promise<releaseProgress> {
    const res = await fetch(`${API_URL}/historical/${date}/${id}`);
    const data = await res.json();
    console.log(data);
    return data;
}
