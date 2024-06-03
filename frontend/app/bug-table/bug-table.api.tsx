import { bug } from "./bug-table.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function getBugs(
    name: string,
    version: string
): Promise<bug[]> {
    const res = await fetch(`${API_URL}/bugs/${name}/${version}`);
    const data = await res.json();
    return data;

}

