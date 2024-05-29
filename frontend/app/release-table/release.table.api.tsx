import { release } from "./release.types";

export async function getRows(): Promise<release[]> {
    const API_URL = 'http://localhost:8080/az_dashboard/'; // Asegúrate de reemplazar esto con la URL real de tu API.

    try {
        const res = await fetch(`${API_URL}releases`);
        const data = await res.json();

        console.log("Data received from API:", data);

        // Verifica si data es un array antes de intentar mapearlo
        if (Array.isArray(data)) {
            return data.map((release: any) => ({
                id_release: release.id_release,
                name: release.name,
                version: release.version,
                engineer_name: release.engineer.name,
                admin_name: release.admin.admin_name,
                code_cutoff: release.code_cutoff,
                init_release_date: release.init_release_date,
                curr_release_date: release.curr_release_date,
                creation_date: release.creation_date,
                last_modification_date: release.last_modification_date,
                is_hotfix: release.is_hotfix,
                status: release.status,
                is_rollback: release.is_rollback,
                release_note: release.release_note,
            }));
        }

        throw new Error("Data is not an array");
    } catch (error) {
        console.error("Error fetching releases", error);
        return [];
    }
}
