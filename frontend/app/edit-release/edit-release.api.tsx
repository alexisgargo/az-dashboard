import { release,release1, engineers} from "@/app/edit-release/release.types";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function putRelease (Release: release,ID:number): Promise<release>{
    const res = await fetch(`${API_URL}/releases/${ID}`,{
        method: 'PUT',
        headers : {
            'Content-Type':'application/json'
        },
        body: JSON.stringify(Release)
    })
    const data = await res.json();
    console.log(data)
    return data; 
}

export async function getEngineers(): Promise<engineers>{
    const res = await fetch(`${API_URL}/engineers`);
    const data = await res.json();
    return data;
}

export async function getRelease(id: number): Promise<release1> {
    const res = await fetch(`${API_URL}/releases/${id}`);
    const data = await res.json();
    return data;
}


