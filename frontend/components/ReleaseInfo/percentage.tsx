import {Button} from '@nextui-org/button'
import {CircularProgress} from "@nextui-org/progress";
import "../styles/release.css"

interface Props {
    porcentaje:number;
    area:string;
}

export const Percentage: React.FC<Props> = ({porcentaje,area}) => {

    return (
        <div>
           <div className='porcentajes'>
            <CircularProgress 
            classNames={{
            svg: "w-36 h-36 drop-shadow-md",
            indicator: "stroke-white",
            track: "stroke-white/10",
            value: "text-3xl font-semibold text-white",
            }}
            value={porcentaje}
            strokeWidth={4}
            showValueLabel={true}>
            </CircularProgress>
            </div>
            <p>{area}</p>
        </div>

        )
}