from rabin_karp import rabin_karp
import unittest


class TestRabinKarp(unittest.TestCase):
    def setUp(self):
        self.text_1 = "THIS IS A TEST TEXT"
        self.pattern_1_1 = "TE"
        self.pattern_1_2 = "TEST"
        self.pattern_1_3 = "IS"
        self.text_2 = "AABAACAADAABAABA"
        self.pattern_2_1 = "AB"
        self.pattern_2_2 = "ACA"
        self.not_matchable_pattern = "Arsen"

    def test_1(self):
        self.assertTupleEqual(rabin_karp(self.text_1, self.pattern_1_1), (10, 15))

    def test_2(self):
        self.assertTupleEqual(rabin_karp(self.text_1, self.pattern_1_2), tuple([10]))

    def test_3(self):
        self.assertTupleEqual(rabin_karp(self.text_1, self.pattern_1_3), (2, 5))

    def test_4(self):
        self.assertTupleEqual(rabin_karp(self.text_2, self.pattern_2_1), (1, 10, 13))

    def test_5(self):
        self.assertTupleEqual(rabin_karp(self.text_2, self.pattern_2_2), tuple([4]))

    def test_no_matches(self):
        self.assertTupleEqual(rabin_karp(self.text_1, self.not_matchable_pattern), ())