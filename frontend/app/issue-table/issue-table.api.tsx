import { issue } from "./issue-table.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function getIssues(
    name: string,
    version: string
): Promise<issue[]> {
    const res = await fetch(`${API_URL}/issue/${name}/${version}`);
    const data = await res.json();
    return data;

}