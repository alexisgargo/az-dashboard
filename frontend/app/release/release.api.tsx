import { release, releaseProgress, issueCount } from "./release.types";

const API_URL = "http://localhost:8080/";

export async function getRelease(id: number): Promise<release> {
    const res = await fetch(`${API_URL}az_dashboard/release/${id}`);
    const data = await res.json();
    // console.log(data);
    return data;
}

export async function getProgress(
    project: string,
    version: string
): Promise<releaseProgress> {
    // console.log(`project: ${project}, version: ${version}`);
    // console.log(`${API_URL}/progress/${project}/${version}`);
    const res = await fetch(
        `${API_URL}az_dashboard/progress/${project}/${version}`
    );
    const data = await res.json();
    // console.log(data);
    return data;
}

export async function getIssueCount(project: string): Promise<issueCount> {
    const res = await fetch(`${API_URL}count/${project}`);
    const data = await res.json();
    console.log(data);
    return data;
}
