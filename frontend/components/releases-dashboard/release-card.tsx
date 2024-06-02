import { Card, CardBody } from "@nextui-org/card";
import { CircularProgress } from "@nextui-org/progress";
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import { useRouter } from "next/navigation";
import { releaseProgress } from "@/app/releases-dashboard/releases-dashboard.types";

export default function ReleaseCard(prop: { releaseInfo: releaseProgress, totalRelease: number }) {
  const router = useRouter()

  const onRedirect = () => {
    router.push('/release')
  }

  return (
    <div className="flex flex-none">
      <Card isPressable onPress={() => onRedirect()}>
        <CardBody>
          <div className="flex flex-row justify-around space-x-5">
            <CircularProgress
              color="success"
              value={prop.totalRelease}
              minValue={0}
              maxValue={100}
              classNames={{ svg: "w-32 h-32", value: "text-3xl font-semibold" }}
              showValueLabel={true}
            />
            <div className="content-center">
              <h1 className="text-xl font-bold py-3">{prop.releaseInfo.release.name}</h1>
              <div className="text-sm">
                <p>QA testing: {prop.releaseInfo.percent_qa}%</p>
                <p>UAT testing: {prop.releaseInfo.percent_uat}%</p>
                <p>PT testing: {prop.releaseInfo.percent_pt}%</p>
                <p>3rd Party testing: {prop.releaseInfo.percent_third_party}%</p>
              </div>
            </div>
          </div>
          <div className="flex flex-row justify-between space-x-5 pt-5">
            <div className="text-sm">
              <p>Release status: {prop.releaseInfo.release.status}</p>
              <p>Code-cutoff date: {prop.releaseInfo.release.code_cutoff}</p>
              <p>Release date: {prop.releaseInfo.release.curr_release_date}</p>
              <p>Release engineer: {prop.releaseInfo.release.engineer.name}</p>
              <p>Release type: {prop.releaseInfo.release.is_hotfix ? "hotfix" : "normal"}</p>
            </div>
            <div className="content-end justify-end">
              <ArrowForwardIosIcon fontSize="large" color="success" />
            </div>
          </div>
        </CardBody>
      </Card>
    </div>
  )
}