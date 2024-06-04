import { Navbar, NavbarBrand, NavbarContent, NavbarItem } from "@nextui-org/navbar";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import Image from "next/image"
import Link from "next/link";

export default function NavBar() {
  return (
    <Navbar isBordered>
      <NavbarBrand>
        <Image alt="Autozone logo" src="/AutoZone-Logo.png" width={250} height={250} />
      </NavbarBrand>

      <NavbarContent justify="center">
        <NavbarItem>
          <Link href={"/"}>Dashboard</Link>
        </NavbarItem>
      </NavbarContent>

      <NavbarContent justify="end">
        <NavbarItem>
          <Link href={"/login"}>
            <AccountCircleIcon />
            Log in
          </Link>
        </NavbarItem>
      </NavbarContent>
    </Navbar>
  )
}