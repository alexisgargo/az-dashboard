const API_URL = "http://localhost:8080/";

export async function login(username: string, password: string): Promise<string> {
    const res = await fetch(`${API_URL}login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    });

    if (!res.ok) {
        const errorText = await res.text();
        throw new Error(errorText);
    }

    return res.text();
}