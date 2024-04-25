import {CircularProgress} from "@nextui-org/progress";
import { title } from "@/components/primitives";
import "../styles/release.css"

interface Props {
    porcentaje:number;
    tickets:number;
    issues:number;
}

export const Release: React.FC<Props> = ({porcentaje,tickets,issues}) => {

    return (
        <div className="conjunto">
            <CircularProgress 
            classNames={{
            svg: "w-36 h-36 drop-shadow-md",
            indicator: "stroke-white",
            value: "text-3xl font-semibold text-white",
            }}
            value={porcentaje}
            strokeWidth={4}
            showValueLabel={true}>
            </CircularProgress>
            
            <div>
                <h1 className={title()}>Release</h1>
                <div>
                    <p>Tickets: {tickets}</p>
                    <p>Issues: {issues}</p>
                </div>
            </div>                
        </div>
        )
}

