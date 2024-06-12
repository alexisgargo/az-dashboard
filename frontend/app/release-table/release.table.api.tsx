import { release } from "./release.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function getRows(): Promise<release[]> {
    try {
        const res = await fetch(`${API_URL}/releases`);
        const data = await res.json();
        data.forEach((release: release) => {
            release.engineer_name = release.engineer.name;
            release.admin_name = release.admin.admin_name;
            release.hotfix = release.is_hotfix ? "Yes" : "No";
            release.rollback = release.is_rollback ? "Yes" : "No";
        });
        return data;
    } catch (error) {
        console.error("Error fetching releases", error);
        return [];
    }
}
