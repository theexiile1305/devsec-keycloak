resource "aws_key_pair" "ssh_key_mfuchs" {
  key_name   = "ssh-key-mfuchs"
  public_key = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIHX9dEii/hLMICq4UnVsleVZ0YaTA5H1QbqiJwMgS19N development@michifuchs.com"
  tags = {
    Usage = "devsec-keycloak"
  }
}