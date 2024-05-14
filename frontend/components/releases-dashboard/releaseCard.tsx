import { Card, CardBody, CardHeader } from "@nextui-org/card";
import { CircularProgress } from "@nextui-org/progress";
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import { useRouter } from "next/navigation";

export default function ReleaseCard() {
  const router = useRouter()
  const is_hotfix = true

  const onRedirect = () => {
    router.push('/release')
  }

  return (
    <Card isPressable onPress={click => onRedirect()}>
      <CardBody>
        <div className="flex flex-row justify-around space-x-5">
          <CircularProgress
            color="success"
            value={69}
            minValue={0}
            maxValue={100}
            classNames={{ svg: "w-32 h-32", value: "text-3xl font-semibold" }}
            showValueLabel={true}
          />
          <div className="content-center">
            <h1 className="text-xl font-bold py-3">ReleaseName 1.2.3</h1>
            <p>QA testing: 69%</p>
            <p>UAT testing: 69%</p>
            <p>PT testing: 69%</p>
            <p>3rd Party testing: 69%</p>
          </div>
        </div>
        <div className="flex flex-row justify-between space-x-5 pt-5">
          <div>
            <p>Release status: on track</p>
            <p>Code-cutoff date: yesterday</p>
            <p>Release date: tomorrow</p>
            <p>Release engineer: Lion</p>
            <p>Release type: {is_hotfix ? "hotfix" : "normal"}</p>
            <p>Notes: this is pretty cool</p>
          </div>
          <div className="content-end justify-end">
            <ArrowForwardIosIcon fontSize="large" color="success" />
          </div>
        </div>
      </CardBody>
    </Card>
  )
}