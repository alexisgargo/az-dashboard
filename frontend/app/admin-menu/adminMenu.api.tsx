import { release } from "../release/release.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function getReleases(): Promise<release[]> {
    const res = await fetch(`${API_URL}/releases`);
    const data = await res.json();
    return data;
}