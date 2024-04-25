import {Button} from '@nextui-org/button'
import {CircularProgress} from "@nextui-org/progress";
import "../styles/release.css"

interface Props {
    porcentaje:number;
    label:string;
    codeCutoff:string;
    releaseEngineer:string;
    releaseGoal:string;
    releaseVersion:number;
}

export const Progress: React.FC<Props> = ({porcentaje,label,codeCutoff,releaseEngineer,releaseGoal,releaseVersion}) => {

    return (
        <div> 
            <p>{label}</p>
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
            <p>{codeCutoff}</p>
            <p>{releaseEngineer}</p>
            <p>{releaseGoal}</p>
            <p>{releaseVersion}</p>
        </div>

        )
}