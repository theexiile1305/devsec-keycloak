resource "aws_instance" "ec2_devsec_keycloak" {
  ami           = "ami-0caef02b518350c8b" # Canonical, Ubuntu, 22.04 LTS, amd64 jammy image build on 2022-09-12
  instance_type = "t3.xlarge"
  root_block_device {
    volume_size = "50"
  }
  associate_public_ip_address = true
  key_name                    = aws_key_pair.ssh_key_mfuchs.key_name
  availability_zone           = "eu-central-1a"
  subnet_id                   = aws_subnet.subnet_devsec_keycloak.id
  vpc_security_group_ids = [
    aws_security_group.sec_group_devsec_keycloak.id
  ]
  depends_on = [
    aws_internet_gateway.igw_devsec_keycloak
  ]
  tags = {
    Usage = "devsec-keycloak"
  }
}