from src.app import hello


def test_hello_returns_expected_string():
    assert hello() == "Hello from the new workspace!"
