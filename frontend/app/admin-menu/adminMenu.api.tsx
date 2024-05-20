import { release } from "../release/release.types";

const API_URL = "http://localhost:8080/";

export async function getReleases(): Promise<release[]> {
    const res = await fetch(`${API_URL}az_dashboard/releases`);
    const data = await res.json();
    // console.log(data);
    return data;
}