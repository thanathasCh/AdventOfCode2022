q = [[eval(y) for y in x.split('\n')] for x in open('input.dat', 'r').read().split('\n\n')]

ans = 0
for (index, (l, r)) in enumerate(q, start=1):
    left, right = [], []

    def unpackList(lst, new_lst):
        while len(lst) > 0:
            v = lst.pop(0)
            if type(v) is list:
                unpackList(v, new_lst)
            else:
                new_lst.append(v)

        new_lst.append(None)


    unpackList(l, left)
    unpackList(r, right)

    idx = 0
    while idx < max(len(left), len(right)):
        if idx >= len(left):
            print(index)
            ans += index
            break

        if idx >= len(right):
            break

        lx = left[idx]
        rx = right[idx]

        if lx is None and rx is None:
            idx += 1
            continue
        elif lx is None and rx is not None:
            print(index)
            ans += index
            break
        elif lx is not None and rx is None:
            break
        elif lx < rx:
            print(index)
            ans += index
            break
        elif rx < lx:
            break
        idx += 1

print(ans)
print('-'*100)

# 5506
with open("./input.dat") as fin:
    parts = fin.read().strip().split("\n\n")


def compare(a, b):
    if isinstance(a, list) and isinstance(b, int):
        b = [b]

    if isinstance(a, int) and isinstance(b, list):
        a = [a]

    if isinstance(a, int) and isinstance(b, int):
        if a < b:
            return 1
        if a == b:
            return 0
        return -1

    if isinstance(a, list) and isinstance(b, list):
        i = 0
        while i < len(a) and i < len(b):
            x = compare(a[i], b[i])
            if x == 1:
                return 1
            if x == -1:
                return -1

            i += 1

        if i == len(a):
            if len(a) == len(b):
                return 0
            return 1  # a ended first

        # If i didn't hit the end of a, it hit the end of b first
        #   This means that b is shorter, which is bad
        return -1


ans = 0

for i, block in enumerate(parts):
    a, b = map(eval, block.split("\n"))
    if compare(a, b) == 1:
        ans += i + 1
        print(i+1)

print(ans)