import { Popover, PopoverTrigger, PopoverContent } from "@nextui-org/popover";
import { Button } from "@nextui-org/button";
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import CalendarComponent from "@/components/Calendar/Calendar";


export default function CalendarRelease(prop: { selectedDate: any}) {
  return (
    <div>

	<Popover placement="left" showArrow={true}>
	<PopoverTrigger>
		<Button isIconOnly variant="light">
		<CalendarMonthIcon />
		</Button>
	</PopoverTrigger>
	<PopoverContent>
		<div className="bg-white">
		<CalendarComponent setDate={prop.selectedDate}/>
		</div>
	</PopoverContent>
	</Popover>
    </div>
  );
}

