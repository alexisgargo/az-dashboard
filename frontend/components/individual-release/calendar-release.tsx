import { Popover, PopoverTrigger, PopoverContent } from "@nextui-org/popover";
import { Button } from "@nextui-org/button";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import CalendarComponent from "@/components/Calendar/Calendar";

export default function CalendarRelease(
    prop: Readonly<{
        selectedDate: any;
        date: string;
    }>
) {
    prop.selectedDate(prop.date);

    return (
        <div>
            <Popover placement="left" showArrow={true}>
                <PopoverTrigger>
                    <Button variant="light">
                        {prop.date}
                        <CalendarMonthIcon />
                    </Button>
                </PopoverTrigger>
                <PopoverContent>
                    <div className="bg-white">
                        <CalendarComponent
                            setDate={prop.selectedDate}
                            date={prop.date}
                        />
                    </div>
                </PopoverContent>
            </Popover>
        </div>
    );
}
