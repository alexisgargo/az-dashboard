import { title } from "@/components/primitives";

import getData from "./release.api";

export default async function ReleasePage() {
	const personajes = await getData();
    
	return (
		<div>
			<h1 className={title()}>Release</h1>
            <div>
                {personajes.map((personaje: { nombre: string }, index: number) => (
                    <p key={index}>{personaje.nombre}</p>
                ))}
            </div>
		</div>
	);
}