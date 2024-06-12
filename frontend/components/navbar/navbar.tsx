import { Navbar, NavbarBrand, NavbarContent, NavbarItem } from "@nextui-org/navbar";
import SupervisorAccountIcon from '@mui/icons-material/SupervisorAccount';
import GridViewIcon from '@mui/icons-material/GridView';
import Image from "next/image"
import Link from "next/link";
import React from "react";

interface NavBarProps {
  isAdminMenu?: boolean
}

const NavBar: React.FC<NavBarProps> = (props: NavBarProps) => {
  return (
    <Navbar isBordered>
      <NavbarBrand>
        <Image alt="Autozone logo" src="/AutoZone-Logo.png" width={250} height={250} />
      </NavbarBrand>

      <NavbarContent justify="center">
        <NavbarItem>
          <Link href={"/"} className="flex flex-row">
            <GridViewIcon />
            <p className="pl-1"> Dashboard </p>
          </Link>
        </NavbarItem>
      </NavbarContent>

      {!props.isAdminMenu ? (
        <NavbarContent justify="end">
          <NavbarItem>
            <Link href={"/login"} className="flex flex-row">
              <SupervisorAccountIcon />
              <p className="pl-1"> Admin menu </p>
            </Link>
          </NavbarItem>
        </NavbarContent>
      ) : (
        <NavbarContent></NavbarContent>
      )}
    </Navbar>
  )
}

export default NavBar;