import "./percentage"
import "../styles/release.css"
import { Percentage } from "./percentage"

interface Props {
    QA:number;
    UAT:number;
    ThirdParty:number;
    PT:number;
}

export const Percentages: React.FC<Props> = ({QA,UAT,ThirdParty,PT}) => {
    return (
        <div className="porcentajes">
           <Percentage area={"QA"} porcentaje={QA}/>
           <Percentage area={"UAT"} porcentaje={UAT}/>
           <Percentage area={"3rd Party"} porcentaje={ThirdParty}/>
           <Percentage area={"PT"} porcentaje={PT} />
        </div>
        )
}