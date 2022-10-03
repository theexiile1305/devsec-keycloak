resource "aws_internet_gateway" "igw_devsec_keycloak" {
  vpc_id = aws_vpc.vpc_devsec_keycloak.id
  tags = {
    Usage = "devsec-keycloak"
  }
}

resource "aws_main_route_table_association" "mrta_devsec_keycloak" {
  vpc_id         = aws_vpc.vpc_devsec_keycloak.id
  route_table_id = aws_route_table.route_igw_devsec_keycloak.id
}

resource "aws_vpc" "vpc_devsec_keycloak" {
  cidr_block = "10.0.0.0/23"
  tags = {
    Usage = "devsec-keycloak"
  }
}

resource "aws_subnet" "subnet_devsec_keycloak" {
  vpc_id            = aws_vpc.vpc_devsec_keycloak.id
  cidr_block        = "10.0.0.0/24"
  availability_zone = "eu-central-1a"
  tags = {
    Usage = "devsec-keycloak"
  }
}

resource "aws_route_table" "route_igw_devsec_keycloak" {
  vpc_id = aws_vpc.vpc_devsec_keycloak.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.igw_devsec_keycloak.id
  }
  route {
    ipv6_cidr_block = "::/0"
    gateway_id      = aws_internet_gateway.igw_devsec_keycloak.id
  }
  tags = {
    Usage = "devsec-keycloak"
  }
}

resource "aws_network_acl" "network_acl_devsec_keycloak" {
  vpc_id     = aws_vpc.vpc_devsec_keycloak.id
  subnet_ids = [aws_subnet.subnet_devsec_keycloak.id]
  ingress {
    action     = "allow"
    rule_no    = 110
    protocol   = "-1"
    to_port    = 0
    from_port  = 0
    cidr_block = "0.0.0.0/0"
  }
  egress {
    action     = "allow"
    rule_no    = 110
    protocol   = "-1"
    to_port    = 0
    from_port  = 0
    cidr_block = "0.0.0.0/0"
  }
  tags = {
    Usage = "devsec-keycloak"
  }
}

resource "aws_security_group" "sec_group_devsec_keycloak" {
  name   = "sec_group_devsec_keycloak"
  vpc_id = aws_vpc.vpc_devsec_keycloak.id
  ingress {
    from_port        = 22
    to_port          = 22
    protocol         = "TCP"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
  ingress {
    from_port        = 80
    to_port          = 80
    protocol         = "TCP"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
  ingress {
    from_port        = 443
    to_port          = 443
    protocol         = "TCP"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
  egress {
    from_port        = 80
    to_port          = 80
    protocol         = "TCP"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
  egress {
    from_port        = 443
    to_port          = 443
    protocol         = "TCP"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
  tags = {
    Usage = "devsec-keycloak"
  }
}