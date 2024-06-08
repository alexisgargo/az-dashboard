const API_URL = "http://localhost:8080/";

export async function login(username: string, password: string): Promise<{ success: boolean; message?: string }> {
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

    const data = await res.json();
    return {
        success: data.success,
        message: data.message // Optional message property for additional information
    };
}