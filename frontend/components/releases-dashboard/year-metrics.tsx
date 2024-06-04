import { Card, CardBody, CardHeader } from "@nextui-org/card"
import { CircularProgress } from "@nextui-org/progress"
import { useEffect, useState } from "react"

export default function YearMetrics(prop: { metrics: number[] }) {
  const [successCalculation, setSuccessCalculation] = useState(0)
  const [hotfixCalculation, setHotfixCalculation] = useState(0)

  useEffect(() => {
    const successCalculator = prop.metrics[0] / (prop.metrics[0] + prop.metrics[2]) * 100
    const hotfixCalculator = prop.metrics[1] / (prop.metrics[0] + prop.metrics[2]) * 100
    setSuccessCalculation(Number.isNaN(successCalculator) ? 0 : successCalculator)
    setHotfixCalculation(Number.isNaN(hotfixCalculator) ? 0 : hotfixCalculator)
  }, [prop])

  return (
    <Card>
      <CardHeader className="justify-center">
        <h1 className="text-xl font-bold">Release metrics of the current fiscal year</h1>
      </CardHeader>
      <CardBody>
        <div className="grow grid grid-cols-2">
          <div className="flex flex-row gap-5 justify-between">
            <CircularProgress
              classNames={{ svg: "w-24 h-24", value: "text-xl font-semibold text-white", }}
              value={successCalculation}
              color={successCalculation > 75 ? 'success' : (successCalculation > 50 ? 'warning' : 'danger')}
              label={'Successful Releases'}
              showValueLabel={true}
            />

            <CircularProgress
              classNames={{ svg: "w-24 h-24", value: "text-xl font-semibold text-white", }}
              value={hotfixCalculation}
              color={'success'}
              label={'Total Hotfixes'}
              showValueLabel={true}
            />
          </div>
          <div className="grid text-lg justify-self-center content-around">
            <p>Successful releases: {prop.metrics[0]}</p>
            <p>Hotfixes: {prop.metrics[1]}</p>
            <p>Rolledback releases: {prop.metrics[2]}</p>
          </div>
        </div>
      </CardBody>
    </Card>
  )
}